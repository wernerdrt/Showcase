package org.educama.shipment.cmmn;

import java.util.Date;

import org.camunda.bpm.engine.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class handles the reevaluation of the case model.
 */
@Component
public class CaseModelHandler {

    @Autowired
    private CaseService caseService;

    /**
     * In the camunda api it is not possible to reevaluate the model. This will
     * be done with saving and updating a case variable.
     */
    public void reevaluateModel(String trackingId) {
        String caseInstanceId = caseService.createCaseExecutionQuery()
                .activityId(ShipmentCaseConstants.SHIPMENT_CASE_PLAN_MODEL).caseInstanceBusinessKey(trackingId)
                .singleResult().getId();
        caseService.withCaseExecution(caseInstanceId)
                .setVariable(ShipmentCaseConstants.VARIABLE_LAST_EVALUATE_DATE, new Date()).execute();
    }

}
