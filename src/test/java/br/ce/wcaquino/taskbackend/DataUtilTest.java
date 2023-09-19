package br.ce.wcaquino.taskbackend;

import br.ce.wcaquino.taskbackend.datautils.utils.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DataUtilTest {
    @Test
    public void dataFuturo(){
        LocalDate date = LocalDate.of(2030,01,01);
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void dataPresente(){
        LocalDate date = LocalDate.of(2023,9,19);
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void dataPassado(){
        LocalDate date = LocalDate.of(2023,8,19);
        Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
    }
}
