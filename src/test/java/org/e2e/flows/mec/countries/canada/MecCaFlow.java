package org.e2e.flows.mec.countries.canada;

import org.e2e.flows.mec.MecCommon;
import org.e2e.flows.mec.countries.canada.locators.MecCaLocator;
import org.e2e.flows.mec.countries.usa.locators.MecUsLocator;

public class MecCaFlow extends MecCommon<MecCaFlow> implements MecCaLocator<MecCaFlow> {

    @Override
    public MecCaFlow fillTransitNumber(String transitNumber) {
        byInputTransitNumber.sendKeys(transitNumber);
        return this;
    }

    @Override
    public MecCaFlow fillInstitutionNumber(String institutionNumber) {
        byInputInstitutionNumber.sendKeys(institutionNumber);
        return this;
    }
}
