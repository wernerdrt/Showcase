package org.educama.shipment.process.tasks;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.task.Task;
import org.educama.shipment.control.ShipmentControlService;
import org.educama.shipment.process.ShipmentCaseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Class representing an interface to the Complete Shipment Order Task.
 */
@Component
public class CompleteShipmentOrderTask {

    private ProcessEngine processEngine;
    private ShipmentControlService shipmentControlService;

    @Autowired
    public CompleteShipmentOrderTask(ProcessEngine processEngine,
                                     ShipmentControlService shipmentControlService) {
        this.processEngine = processEngine;
        this.shipmentControlService = shipmentControlService;
    }

    public boolean isActive(String trackingId) {
        return processEngine.getCaseService().createCaseExecutionQuery()
                .activityId(ShipmentCaseConstants.PLAN_ITEM_HUMAN_TASK_COMPLETE_SHIPMENT_ORDER)
                .caseInstanceBusinessKey(trackingId)
                .active()
                .singleResult() != null;
    }

    public boolean canBeCompleted(String trackingId) {
        return shipmentControlService.isShipmentOrderComplete(trackingId);
    }

    public void complete(String trackingId) {
        CaseExecution completeShipmentOrderCaseExecution = processEngine.getCaseService().createCaseExecutionQuery()
                .activityId(ShipmentCaseConstants.PLAN_ITEM_HUMAN_TASK_COMPLETE_SHIPMENT_ORDER)
                .caseInstanceBusinessKey(trackingId)
                .active()
                .singleResult();

        Assert.notNull(completeShipmentOrderCaseExecution);

        Task task = processEngine.getTaskService().createTaskQuery()
                .caseExecutionId(completeShipmentOrderCaseExecution.getId())
                .singleResult();

        Assert.notNull(task);

        processEngine.getTaskService().complete(task.getId());
    }
}
