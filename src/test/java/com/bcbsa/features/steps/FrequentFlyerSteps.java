package com.bcbsa.features.steps;

import com.bcbsa.bo.FrequentFlyer;
import com.bcbsa.helper.Status;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FrequentFlyerSteps {
    FrequentFlyer frequentFlyer;

    @Step
    public void a_traveller_has_a_frequent_flyer_account_with_balance(int initialBalance) {
        frequentFlyer = FrequentFlyer.withInitialBalanceOf(initialBalance);
    }

    @Step
    public void the_traveller_flies(int distance) {
        frequentFlyer.flies(distance).kilometers();
    }

    @Step
    public void traveller_should_have_a_balance_of(int expectedBalance) {
        assertThat(frequentFlyer.getBalance()).isEqualTo(expectedBalance);
    }

    @Step
    public void a_traveller_joins_the_frequent_flyer_program() {
        frequentFlyer = FrequentFlyer.withInitialBalanceOf(0);
    }

    @Step
    public void traveller_should_have_a_status_of(Status expectedStatus) {
        assertThat(frequentFlyer.getStatus()).isEqualTo(expectedStatus.getText());
    }
}
