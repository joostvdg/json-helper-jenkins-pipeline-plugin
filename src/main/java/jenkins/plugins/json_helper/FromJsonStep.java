package jenkins.plugins.json_helper;


import groovy.json.JsonSlurper;
import hudson.Extension;
import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractStepImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractSynchronousNonBlockingStepExecution;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.inject.Inject;
import java.io.StringReader;

/**
 * For transforming JSON into a generic Groovy object.
 * This will be done by @see {@link JsonSlurper}
 *
 * @author Joost van der Griendt
 */
public class FromJsonStep extends AbstractStepImpl {

    private String jsonString;

    private JsonSlurper jsonParser;

    @DataBoundConstructor
    public FromJsonStep(String jsonString) {
        jsonParser = new JsonSlurper();
        this.setJsonString(jsonString);
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public Object fromJson() {
        Object jsonObject = jsonParser.parse(new StringReader(getJsonString()));
        return jsonObject;
    }


    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }

    @Extension
    public static final class DescriptorImpl extends AbstractStepDescriptorImpl {

        public DescriptorImpl() {
            super(Execution.class);
        }

        @Override
        public String getFunctionName() {
            return "writeObjectFromJson";
        }

        @Override
        public String getDisplayName() {
            return "JSON Helper :: Write Object From a JSON string";
        }

    }

    public static final class Execution extends AbstractSynchronousNonBlockingStepExecution<Object> {

        @Inject
        private transient FromJsonStep step;

        @Override
        protected Object run() throws Exception {
            return step.fromJson();
        }

    }
}
