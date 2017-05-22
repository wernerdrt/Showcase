package org.educama.shipment.cmmn;

/**
 * This class contains all model constants of the case diagram ShipmentCase.cmmn
 * .
 */
public final class ShipmentCaseConstants {

    private ShipmentCaseConstants() {
    }

    public static final String SHIPMENTCASEKEY = "ShipmentCase";

    // Human Tasks
    public static final String PLAN_ITEM_HUMAN_TASK_CREATE_INVOICE = "PlanItem_HumanTask_CreateInvoice";
    public static final String PLAN_ITEM_HUMAN_TASK_CHANGE_SHIPMENT_ORDER = "PlanItem_HumanTask_ChangeShipmentOrder";
    public static final String PLAN_ITEM_HUMAN_TASK_COMPLETE_SHIPMENT_ORDER = "PlanItem_HumanTask_CompleteShipmentOrder";

    // Stages
    public static final String PLAN_ITEM_STAGE_PROCESS_SHIPMENT_ORDER = "PlanItem_Stage_ProcessShipmentOrder";

    // Milestones
    public static final String PLAN_ITEM_MILESTONE_SHIPMENT_ORDER_COMPLETED = "PlanItem_Milestone_ShipmentOrderCompleted";

}
