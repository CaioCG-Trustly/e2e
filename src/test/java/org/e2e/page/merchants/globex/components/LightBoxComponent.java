package org.e2e.page.merchants.globex.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.e2e.connectors.Flow;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class LightBoxComponent {

    static final SelenideElement byIFrameLightBox = $("#paywithmybank-iframe");
    static final SelenideElement byInputSearchBankList = $("#lbx-listBank-inputSearch");
    static final SelenideElement byItemOfBankList =  $(".bank-list--virtualize-item");

    static final SelenideElement byButtonEnterAccountManually = $("div[class='bank-list--noResultsFound'] > button");
    static final SelenideElement byInputIban =  $(".input_container > input");
    static final SelenideElement byDivUlIbanResults =  $(".input_container > .results > li.marked > div");
    static final SelenideElement byInputFirstName = $("#lbx-formEcheck-inputfirstName");
    static final SelenideElement byInputLastName = $("#lbx-formEcheck-inputlastName");
    static final SelenideElement byInputIbanMCD =  $("#lbx-formEcheck-inputiban");
    static final SelenideElement byInputSortCode =  $("#lbx-formEcheck-inputsortCode");
    static final SelenideElement byInputAccountNumber =  $("#lbx-formEcheck-inputaccountNumber");
    static final SelenideElement byButtonContinueMCD =  $("#lbx-formEcheck-submit");
    static final SelenideElement byButtonOkMCD =  $("#lbx-challengeDeposit-close");
    static final SelenideElement byInputNameOnAccount =  $("#lbx-formEcheck-inputownerName");
    static final SelenideElement byInputTransitNumber =  $("#lbx-formEcheck-inputtransitNumber");
    static final SelenideElement byInputFinancialInstitutionNumber =  $("#lbx-formEcheck-inputfinancialInstitutionNumber");
    static final SelenideElement bySelectAccountType =  $("#lbx-formEcheck-selectaccountType");



    public LightBoxComponent selectBank(String bankName) {
        $("#paywithmybank-loading-spinner").shouldNotBe(Condition.visible, Duration.ofSeconds(10));
        searchFor(bankName);
        byItemOfBankList.click();
        return this;
    }

    public LightBoxComponent searchFor(String bankName) {
        switchToLightBoxIFrame();
        try {
            $(".selectBank-page").should(Condition.visible, Duration.ofSeconds(10));
        } catch(Exception e) {
            // do nothing
        }
        byInputSearchBankList.shouldBe(Condition.editable);
        byInputSearchBankList.sendKeys(bankName);
        return this;
    }

    public LightBoxComponent clickOnEnterAccountManually() {
        byButtonEnterAccountManually.click();
        return this;
    }

    public LightBoxComponent startFlow(Flow executionFlow) {
        executionFlow.execute();
        return this;
    }

    // TODO parece que o preechimento do Iban nao deve ser responsabilidade do lightbox - ver como remover isso daqui
    public LightBoxComponent fillIBAN(String IBAN) {
        switchTo().parentFrame();
        switchTo().frame("paywithmybank-iframe");
        switchTo().frame("trustlyabFrame");

        byInputIban.sendKeys(IBAN);

        byDivUlIbanResults.click();
        return this;
    }

    // TODO parece que o preechimento do Iban nao deve ser responsabilidade do lightbox - ver como remover isso daqui
    public LightBoxComponent clickOnContinueForIban() {
        $(".button_next").click();
        return this;
    }

    public LightBoxComponent switchToLightBoxIFrame() {
        switchTo().parentFrame();
        switchTo().frame(byIFrameLightBox);
        return this;
    }

    public void waitUntilClose() {
        $("#paywithmybank-lightbox").should(Condition.disappear, Duration.ofSeconds(10));
    }

    public LightBoxComponent fillFirstName(String firstName) {
        switchToLightBoxIFrame();
        byInputFirstName.sendKeys(firstName);
        return this;
    }

    public LightBoxComponent fillLastName(String lastName) {
        byInputLastName.sendKeys(lastName);
        return this;
    }

    public LightBoxComponent fillIbanMcd(String iban) {
        byInputIbanMCD.sendKeys(iban);
        return this;
    }

    public LightBoxComponent clickOnContinueForMcd() {
        byButtonContinueMCD.click();
        return this;
    }

    public LightBoxComponent clickOnOkForDepositChallenge() {
        byButtonOkMCD.shouldBe(Condition.visible, Duration.ofSeconds(10));
        byButtonOkMCD.click();
        return this;
    }

    public LightBoxComponent fillSortCode(String sortCode) {
        byInputSortCode.sendKeys(sortCode);
        return this;
    }

    public LightBoxComponent fillAccountNumber(String accountNumber) {
        byInputAccountNumber.sendKeys(accountNumber);
        return this;
    }

    public LightBoxComponent fillNameOnAccount(String name) {
        switchToLightBoxIFrame();
        byInputNameOnAccount.sendKeys(name);
        return this;
    }

    public LightBoxComponent fillTransitNumber(String transitNumber) {
        byInputTransitNumber.sendKeys(transitNumber);
        return this;
    }

    public LightBoxComponent fillFinancialInstitutionNumber(String fiNumber) {
        byInputFinancialInstitutionNumber.sendKeys(fiNumber);
        return this;
    }

    public LightBoxComponent selectAccountType(String accountType) {
        bySelectAccountType.selectOption(accountType);
        return this;
    }
}
