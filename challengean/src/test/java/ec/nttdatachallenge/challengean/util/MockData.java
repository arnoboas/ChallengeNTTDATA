package ec.nttdatachallenge.challengean.util;

import com.google.gson.Gson;

import ec.nttdatachallenge.challengean.domain.enums.CryptocurrencyEnum;
import ec.nttdatachallenge.challengean.domain.models.RequestGenerateQuote;
import ec.nttdatachallenge.challengean.domain.models.RequestGenerateReport;
import ec.nttdatachallenge.challengean.domain.models.RequestSaveQuote;
import ec.nttdatachallenge.challengean.domain.models.VersionHyundai;
import ec.nttdatachallenge.challengean.domain.models.api.ReqGQApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ReqGRApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ReqSQApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ResGQApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ResGRApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ResSQApiModel;
import ec.nttdatachallenge.challengean.domain.models.cache.QuoteCacheModel;
import ec.nttdatachallenge.challengean.domain.models.external.CarMExtM;
import ec.nttdatachallenge.challengean.domain.models.external.CryptocurrencyExtM;
import ec.nttdatachallenge.challengean.domain.models.persistence.CarPesistenceModel;
import ec.nttdatachallenge.challengean.domain.models.persistence.QuotePersistenceModel;
import ec.nttdatachallenge.challengean.infraestructure.adapter.out.cache.entity.QuoteRedis;
import ec.nttdatachallenge.challengean.infraestructure.adapter.out.cache.entity.VersionRedis;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class MockData {

    public static CarPesistenceModel getCarPersistenceModel() {
    	CarPesistenceModel carPersistenceModel = new CarPesistenceModel();
        carPersistenceModel.setIdModel(Constants.ACCENT_MODEL);
        carPersistenceModel.setIdVehicle(Constants.ID_VERSION);
        carPersistenceModel.setVersion(Constants.VERSION_ACCENT_1);
        carPersistenceModel.setPriceUsd(new BigDecimal("100000"));
        return carPersistenceModel;

    }

    public static CarMExtM getCarModelExternalModel1() {
    	CarMExtM carModelExternalModel = new CarMExtM();
        carModelExternalModel.setPriceUsd(Constants.PRICE_VERSION_ACCENT_1);
        carModelExternalModel.setVersion(Constants.VERSION_ACCENT_1);

        return carModelExternalModel;
    }

    public static CarMExtM getCarModelExternalModel2() {
    	CarMExtM carModelExternalModel2 = new CarMExtM();
        carModelExternalModel2.setPriceUsd(Constants.PRICE_VERSION_ACCENT_2);
        carModelExternalModel2.setVersion(Constants.VERSION_ACCENT_2);

        return carModelExternalModel2;
    }

    public static List<CarMExtM> getCarModelExternalModel() {
        return Arrays.asList(getCarModelExternalModel1(), getCarModelExternalModel2());
    }

    public static ReqGQApiModel getRequestGenerateQuoteApiModel() {
    	ReqGQApiModel request = new ReqGQApiModel();
        request.setModel(Constants.ACCENT_MODEL);
        request.setCryptocurrency(CryptocurrencyEnum.BTC);
        return request;
    }

    public static CryptocurrencyExtM getCryptocurrencyExternalModel() {
    	CryptocurrencyExtM cryptocurrencyExternalModel = new CryptocurrencyExtM();
        cryptocurrencyExternalModel.setCode(Constants.CRYPTOCURRENCY_BTC);
        cryptocurrencyExternalModel.setPriceUsd(Constants.PRICE_CRYPTOCURRENCY);
        return cryptocurrencyExternalModel;
    }

    public static QuoteCacheModel getQuoteCacheModel() {
        QuoteCacheModel quoteCacheModel = new QuoteCacheModel();
        quoteCacheModel.setConvertionId(Constants.CONVERTION_ID);
        quoteCacheModel.setConversionTimelife(Constants.CONVERTION_TTL);
        quoteCacheModel.setVersions(getVersions());
        return quoteCacheModel;
    }

    private static Set<VersionHyundai> getVersions() {
    	VersionHyundai version = new VersionHyundai();
        version.setVersion(Constants.VERSION_ACCENT_1);
        version.setModel(Constants.ACCENT_MODEL);
        version.setPriceCryptocurrency(Constants.PRICE_CRYPTOCURRENCY);
        version.setCryptocurrency(Constants.CRYPTOCURRENCY_BTC);
        version.setPriceUsd(Constants.PRICE_VERSION_ACCENT_1);
        VersionHyundai version2 = new VersionHyundai();
        version2.setVersion(Constants.VERSION_ACCENT_2);
        version2.setModel(Constants.ACCENT_MODEL);
        version2.setPriceCryptocurrency(Constants.PRICE_CRYPTOCURRENCY);
        version2.setCryptocurrency(Constants.CRYPTOCURRENCY_BTC);
        version2.setPriceUsd(Constants.PRICE_VERSION_ACCENT_2);
        return Set.of(version, version2);
    }

    private static Set<VersionRedis> getVersionRedis() {
        VersionRedis versionRedis = new VersionRedis();
        versionRedis.setVersion(Constants.VERSION_ACCENT_1);
        versionRedis.setModel(Constants.ACCENT_MODEL);
        versionRedis.setPriceCryptocurrency(Constants.PRICE_CRYPTOCURRENCY);
        versionRedis.setCryptocurrency(Constants.CRYPTOCURRENCY_BTC);
        versionRedis.setPriceUsd(Constants.PRICE_VERSION_ACCENT_1);
        VersionRedis version2 = new VersionRedis();
        version2.setVersion(Constants.VERSION_ACCENT_2);
        version2.setModel(Constants.ACCENT_MODEL);
        version2.setPriceCryptocurrency(Constants.PRICE_CRYPTOCURRENCY);
        version2.setCryptocurrency(Constants.CRYPTOCURRENCY_BTC);
        version2.setPriceUsd(Constants.PRICE_VERSION_ACCENT_2);
        return Set.of(versionRedis, version2);
    }

    public static QuotePersistenceModel getQuotePersistenceModel() {
        QuotePersistenceModel quotePersistenceModel = new QuotePersistenceModel();
        quotePersistenceModel.setModel(Constants.ACCENT_MODEL);
        quotePersistenceModel.setFullName(Constants.FULL_NAME);
        quotePersistenceModel.setCryptocurrency(Constants.CRYPTOCURRENCY_BTC);
        quotePersistenceModel.setCreateDate(new Date());
        quotePersistenceModel.setId(1);
        quotePersistenceModel.setPurchaseId(UUID.randomUUID().toString());
        quotePersistenceModel.setVersion(Constants.VERSION_ACCENT_1);
        quotePersistenceModel.setPriceCryptocurrency(Constants.PRICE_CRYPTOCURRENCY);
        quotePersistenceModel.setPriceUsd(Constants.PRICE_VERSION_ACCENT_1);
        return quotePersistenceModel;
    }

    public static QuotePersistenceModel getQuotePersistenceModel(String model, String version, BigDecimal priceCryptocurrency, BigDecimal priceUsd) {
        QuotePersistenceModel quote = new QuotePersistenceModel();
        quote.setModel(model);
        quote.setVersion(version);
        quote.setFullName(Constants.FULL_NAME);
        quote.setCryptocurrency(Constants.CRYPTOCURRENCY_BTC);
        quote.setCreateDate(new Date());
        quote.setId(1);
        quote.setPurchaseId(UUID.randomUUID().toString());
        quote.setPriceCryptocurrency(priceCryptocurrency);
        quote.setPriceUsd(priceUsd);
        return quote;
    }

    public static ReqSQApiModel getRequestSaveQuoteApiModel() {
    	ReqSQApiModel requestSaveQuoteApiModel = new ReqSQApiModel();
        requestSaveQuoteApiModel.setModel(Constants.ACCENT_MODEL);
        requestSaveQuoteApiModel.setVersion(Constants.VERSION_ACCENT_1);
        requestSaveQuoteApiModel.setCryptocurrency(Constants.CRYPTOCURRENCY_BTC);
        requestSaveQuoteApiModel.setConvertionId(UUID.randomUUID().toString());
        requestSaveQuoteApiModel.setFullName(Constants.FULL_NAME);
        return requestSaveQuoteApiModel;
    }

    public static ReqGRApiModel getRequestGenerateReportApiModel() {
    	ReqGRApiModel requestGenerateReportApiModel = new ReqGRApiModel();
        requestGenerateReportApiModel.setCryptocurrency(CryptocurrencyEnum.BTC);
        requestGenerateReportApiModel.setModel(Constants.ACCENT_MODEL);
        requestGenerateReportApiModel.setDate(LocalDate.now());
        return requestGenerateReportApiModel;
    }


    public static ResGQApiModel getResponseGenerateQuoteApiModel() {
    	ResGQApiModel responseGenerateQuoteApiModel = new ResGQApiModel();
        responseGenerateQuoteApiModel.setConversionTimelife(Constants.CONVERTION_TTL);
        responseGenerateQuoteApiModel.setVersions(getVersions());
        responseGenerateQuoteApiModel.setConvertionId(UUID.randomUUID().toString());
        return responseGenerateQuoteApiModel;
    }

    public static ResSQApiModel getResponseSaveQuoteApiModel() {
    	ResSQApiModel responseSaveQuoteApiModel = new ResSQApiModel();
        responseSaveQuoteApiModel.setModel(Constants.ACCENT_MODEL);
        responseSaveQuoteApiModel.setCryptocurrency(CryptocurrencyEnum.BTC);
        responseSaveQuoteApiModel.setDate("2023-12-05");
        responseSaveQuoteApiModel.setVersion(Constants.VERSION_ACCENT_1);
        responseSaveQuoteApiModel.setFullName(Constants.FULL_NAME);
        responseSaveQuoteApiModel.setPriceCryptocurrency(Constants.PRICE_CRYPTOCURRENCY);
        responseSaveQuoteApiModel.setPriceUsd(Constants.PRICE_VERSION_ACCENT_1.toString());
        responseSaveQuoteApiModel.setPurchaseId(UUID.randomUUID().toString());
        return responseSaveQuoteApiModel;
    }

    public static RequestGenerateQuote getRequestGenerateQuote() {
        return new Gson().fromJson("{'data': {'model': 'ACCENT', 'cryptocurrency': 'BTC'} }", RequestGenerateQuote.class);
    }

    public static RequestSaveQuote getRequestSaveQuote() {
        return new Gson().fromJson("{'data': {'cryptocurrency': 'ETH', 'convertionId': '43bc3bea-0b44-4700-8b7a-fbd5b2ad8c28', 'fullName': 'demostracion', 'model': 'ACCENT', 'version': 'ALL NEW ACCENT GL'} }", RequestSaveQuote.class);
    }

    public static RequestGenerateReport getRequestGenerateReport() {
        return new Gson().fromJson("{'data': {'date': '2023-07-12', 'model': 'ACCENT', 'cryptocurrency': 'ETH'} }", RequestGenerateReport.class);
    }


    public static ResGRApiModel getResponseGenerateReportApiModel(){
    	ResGRApiModel responseGenerateReportApiModel = new ResGRApiModel();
        responseGenerateReportApiModel.setModel(Constants.ACCENT_MODEL);
        responseGenerateReportApiModel.setCryptocurrency(Constants.CRYPTOCURRENCY_BTC);
        responseGenerateReportApiModel.setDate(LocalDate.now());
        responseGenerateReportApiModel.setVersion(Constants.VERSION_ACCENT_1);
        responseGenerateReportApiModel.setUsdAmount(Constants.PRICE_VERSION_ACCENT_1);
        responseGenerateReportApiModel.setCryptocurrencyAmount(Constants.PRICE_CRYPTOCURRENCY);
        return responseGenerateReportApiModel;
    }


    public static QuoteRedis getQuoteRedis(){
        QuoteRedis quoteRedis = new QuoteRedis();
        quoteRedis.setConvertionId(UUID.randomUUID().toString());
        quoteRedis.setVersions(getVersionRedis());
        quoteRedis.setConversionTimelife(Constants.CONVERTION_TTL);
        return quoteRedis;
    }
}
