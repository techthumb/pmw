package org.sterl.pmw.boundary;

import org.sterl.pmw.model.AbstractWorkflowContext;
import org.sterl.pmw.model.Workflow;

public interface WorkflowService<RegistryType> {
    <T extends AbstractWorkflowContext> String execute(Workflow<T> w);
    <T extends AbstractWorkflowContext> String execute(Workflow<T> w, T c);
    void clearAllWorkflows();
    public <T extends AbstractWorkflowContext> RegistryType register(Workflow<T> w);
}
