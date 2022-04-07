package handler;

import domain.InvoiceTotal;
import domain.PriceList;
import domain.Usage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static domain.constant.PackageName.PACKAGE_S;
import static list.PackageList.packages;

class InvoiceHandlerTest {

    private final InvoiceHandler invoiceHandler = new InvoiceHandler();
    private final PriceList priceList = new PriceList();


    @Test
    void test_calculateInvoiceTotal_when_extraSms_and_extraMinutes_arePresent() {
        //13 minutes = +0.6 euro
        //60 sms = +3 euro
        List<Usage> usages = new ArrayList<>();
        usages.add(new Usage(1L, 2L, 6L));
        usages.add(new Usage(2L, 2L, 7L));
        usages.add(new Usage(1L, 1L, 60L));

        InvoiceTotal invoiceTotal = invoiceHandler.calculateInvoiceTotal(usages, priceList, packages.get(PACKAGE_S));

        Double totalPriceExpected = 5.0d + 3.0d + 0.6;
        Assertions.assertEquals(totalPriceExpected, invoiceTotal.getTotalPrice());
    }

    @Test
    void test_calculateInvoiceTotal_when_onlyExtraSms_isPresent() {
        //10 minutes = +0 euro
        //60 sms = +3 euro
        List<Usage> usages = new ArrayList<>();
        usages.add(new Usage(1L, 2L, 6L));
        usages.add(new Usage(2L, 2L, 4L));
        usages.add(new Usage(1L, 1L, 60L));

        InvoiceTotal invoiceTotal = invoiceHandler.calculateInvoiceTotal(usages, priceList, packages.get(PACKAGE_S));

        Double totalPriceExpected = 5.0d + 3.0d;
        Assertions.assertEquals(totalPriceExpected, invoiceTotal.getTotalPrice());
    }

    @Test
    void test_calculateInvoiceTotal_when_onlyExtraMinutes_isPresent() {
        //13 minutes = +0.6 euro
        //49 = 0 euro
        List<Usage> usages = new ArrayList<>();
        usages.add(new Usage(1L, 2L, 6L));
        usages.add(new Usage(2L, 2L, 7L));
        usages.add(new Usage(1L, 1L, 49L));

        InvoiceTotal invoiceTotal = invoiceHandler.calculateInvoiceTotal(usages, priceList, packages.get(PACKAGE_S));

        Double totalPriceExpected = 5.0d + 0.6;
        Assertions.assertEquals(totalPriceExpected, invoiceTotal.getTotalPrice());
    }

}