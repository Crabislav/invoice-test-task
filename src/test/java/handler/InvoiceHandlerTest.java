package handler;

import domain.InvoiceTotal;
import domain.Package;
import domain.PriceList;
import domain.Usage;
import domain.constant.PackageName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.PackageUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static domain.constant.PackageName.PACKAGE_S;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.PriceListUtils.getPackagePrices;
import static utils.PriceListUtils.getServicePrices;

class InvoiceHandlerTest {

    private final InvoiceHandler invoiceHandler = new InvoiceHandler();

    private static PriceList priceList;
    private static Map<PackageName, Package> packageMap;

    @BeforeAll
    static void setUp() {
        priceList = new PriceList(getServicePrices(), getPackagePrices());
        packageMap = PackageUtils.getPackages();
    }

    @Test
    void test_calculateInvoiceTotal_when_extraSms_and_extraMinutes_arePresent() {
        //13 minutes = +0.6 euro
        //60 sms = +3 euro
        List<Usage> usages = new ArrayList<>();
        usages.add(new Usage(1L, 2L, 6L));
        usages.add(new Usage(2L, 2L, 7L));
        usages.add(new Usage(1L, 1L, 60L));

        InvoiceTotal invoiceTotal = invoiceHandler.calculateInvoiceTotal(usages, priceList, packageMap.get(PACKAGE_S));

        BigDecimal totalPriceExpected = new BigDecimal("5")
                .add(new BigDecimal("3"))
                .add(new BigDecimal("0.6"));

        assertEquals(totalPriceExpected, invoiceTotal.getTotalPrice());
        assertEquals(10, invoiceTotal.getExtraSMS());
        assertEquals(3, invoiceTotal.getExtraMinutes());
        assertEquals(PACKAGE_S, invoiceTotal.getPackageName());
    }

    @Test
    void test_calculateInvoiceTotal_when_onlyExtraSms_isPresent() {
        //10 minutes = +0 euro
        //60 sms = +3 euro
        List<Usage> usages = new ArrayList<>();
        usages.add(new Usage(1L, 2L, 6L));
        usages.add(new Usage(2L, 2L, 4L));
        usages.add(new Usage(1L, 1L, 60L));

        InvoiceTotal invoiceTotal = invoiceHandler.calculateInvoiceTotal(usages, priceList, packageMap.get(PACKAGE_S));

        BigDecimal totalPriceExpected = new BigDecimal("5")
                .add(new BigDecimal("3"))
                .setScale(1, RoundingMode.UP);

        assertEquals(totalPriceExpected, invoiceTotal.getTotalPrice());
        assertEquals(10, invoiceTotal.getExtraSMS());
        assertEquals(0, invoiceTotal.getExtraMinutes());
        assertEquals(PACKAGE_S, invoiceTotal.getPackageName());
    }

    @Test
    void test_calculateInvoiceTotal_when_onlyExtraMinutes_isPresent() {
        //13 minutes = +0.6 euro
        //49 sms = 0 euro
        List<Usage> usages = new ArrayList<>();
        usages.add(new Usage(1L, 2L, 6L));
        usages.add(new Usage(2L, 2L, 7L));
        usages.add(new Usage(1L, 1L, 49L));

        InvoiceTotal invoiceTotal = invoiceHandler.calculateInvoiceTotal(usages, priceList, packageMap.get(PACKAGE_S));

        BigDecimal totalPriceExpected = new BigDecimal("5")
                .add(new BigDecimal("0.6"));

        assertEquals(totalPriceExpected, invoiceTotal.getTotalPrice());
        assertEquals(-1, invoiceTotal.getExtraSMS());
        assertEquals(3, invoiceTotal.getExtraMinutes());
        assertEquals(PACKAGE_S, invoiceTotal.getPackageName());
    }

}