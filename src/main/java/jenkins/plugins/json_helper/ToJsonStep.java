package jenkins.plugins.json_helper;

import com.google.gson.Gson;
import hudson.Extension;
import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractStepImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractSynchronousNonBlockingStepExecution;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.inject.Inject;

/**
 * For transforming Groovy objects to JSON.
 *
 * @author Joost van der Griendt
 */
public class ToJsonStep extends AbstractStepImpl {

    private static final long serialVersionUID = 1L;

    private Object jsonObject;

    private Gson gson;

    @DataBoundConstructor
    public ToJsonStep(Object jsonObject) {
        gson = new Gson();
        this.setJsonObject(jsonObject);
    }


    public String toJson() {
        String jsonString = gson.toJson(getJsonObject());
        return jsonString;
    }


    @Override
    public ToJsonStep.DescriptorImpl getDescriptor() {
        return (ToJsonStep.DescriptorImpl) super.getDescriptor();
    }

    public Object getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(Object jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Extension
    public static final class DescriptorImpl extends AbstractStepDescriptorImpl {

        public DescriptorImpl() {
            super(Execution.class);
        }

        @Override
        public String getFunctionName() {
            return "writeObjectToJson";
        }

        @Override
        public String getDisplayName() {
            return "JSON Helper :: Write Object To JSON String";
        }

    }

    public static final class Execution extends AbstractSynchronousNonBlockingStepExecution<String> {

        @Inject
        private transient ToJsonStep step;

        @Override
        protected String run() throws Exception {
            return step.toJson();
        }

    }

}
