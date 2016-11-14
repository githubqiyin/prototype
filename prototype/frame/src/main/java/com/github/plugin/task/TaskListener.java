package com.github.plugin.task;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerKey;
import org.quartz.TriggerListener;

public class TaskListener implements JobListener, TriggerListener {

    private static final Logger log = Logger.getLogger(TaskListener.class);

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        JobKey jobKey = context.getJobDetail().getKey();
        log.info("任务拒绝： [" + jobKey + "]");
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        JobKey jobKey = context.getJobDetail().getKey();
        log.info("任务启动： [" + jobKey + "]");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException e) {
        JobKey jobKey = context.getJobDetail().getKey();
        log.info("任务完成： [" + jobKey + "]");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobexecutioncontext, CompletedExecutionInstruction completedexecutioninstruction) {
        TriggerKey triggerKey = trigger.getKey();
        log.info("触发器完成： [" + triggerKey + "]");
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobexecutioncontext) {
        TriggerKey triggerKey = trigger.getKey();
        log.info("触发器启动： [" + triggerKey + "]");
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        TriggerKey triggerKey = trigger.getKey();
        log.info("触发器哑火： [" + triggerKey + "]");
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobexecutioncontext) {
        return false;
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

}