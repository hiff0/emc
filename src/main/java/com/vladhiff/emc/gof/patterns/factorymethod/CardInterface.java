package com.vladhiff.emc.gof.patterns.factorymethod;

import java.math.BigDecimal;

public interface CardInterface {
    BigDecimal getCreditLimit();
    BigDecimal getAnnualCharge();
}
