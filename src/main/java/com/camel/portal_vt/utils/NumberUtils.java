package com.camel.portal_vt.utils;

import com.camel.portal_vt.dtos.google.TransitDetails;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

public class NumberUtils {

    public static BigDecimal sumTotalValueRoute(List<TransitDetails> transitDetailsList) {
        BigDecimal total = BigDecimal.valueOf(0);

        if (transitDetailsList != null || !transitDetailsList.isEmpty()) {

            for(TransitDetails item : transitDetailsList){
                total = total.add(item.line().vehicle().value());
            };
        }

        return total;
    }

    public static String formatToBRL(BigDecimal value) {

        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            return "R$ 0,00";
        }

        DecimalFormat formatedValue = new DecimalFormat("R$ #,##0.00");

        return formatedValue.format(value);
    }
}
