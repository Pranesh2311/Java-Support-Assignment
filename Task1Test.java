package Assignment;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class Task1Test {

    @Test
    public void testNullAccountsList() {

        Task1 service = new Task1();

        List<LoanAccount> result =
                service.getOverdueLoans(null);

        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}