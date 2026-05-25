package Assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Task5 {

    // FIX: Added SLF4J logger for controlled logging
    private static final Logger logger =
            LoggerFactory.getLogger(Task5.class);

    public ValidationResult validate(Document doc) {

        try {

            if (doc == null) {

                // FIX: Validation failure handled separately
                logger.warn("Validation failed: Document is null");

                return ValidationResult.invalid("Document is null");
            }

            String content = doc.extractContent();

            if (content == null || content.isEmpty()) {

                // FIX: Validation failure logged as warning instead of exception stack trace
                logger.warn("Validation failed: Empty document content");

                return ValidationResult.invalid("Empty content");
            }

            return runValidationRules(content);

        } catch (Exception e) {

            // FIX: Unexpected runtime errors logged properly using SLF4J
            logger.error("Unexpected error during document validation", e);

            // FIX: Avoid returning null
            return ValidationResult.invalid("Validation failed");
        }
    }

    public void validateBatch(List<Document> docs) {

        for (Document doc : docs) {

            try {

                ValidationResult r = validate(doc);

                // FIX: Added null safety before calling isValid()
                if (r != null && r.isValid()) {

                    saveResult(r);

                } else {

                    logger.warn("Invalid document skipped");
                }

            } catch (Exception e) {

                // FIX: Do not silently swallow exceptions
                logger.error("Error while validating batch document", e);
            }
        }
    }

    private ValidationResult runValidationRules(String content) {

        return new ValidationResult(true);
    }

    private void saveResult(ValidationResult result) {

    }
}

class Document {

    public String extractContent() {

        return "sample";
    }
}

class ValidationResult {

    private boolean valid;
    private String message;

    public ValidationResult(boolean valid) {

        this.valid = valid;
    }

    public static ValidationResult invalid(String message) {

        ValidationResult result = new ValidationResult(false);
        result.message = message;
        return result;
    }

    public boolean isValid() {

        return valid;
    }
}
