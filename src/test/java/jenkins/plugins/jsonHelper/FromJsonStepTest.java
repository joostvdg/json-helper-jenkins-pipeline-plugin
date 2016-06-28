package jenkins.plugins.jsonHelper;

import hudson.model.Hudson;
import jenkins.model.Jenkins;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author Joost van der Griendt
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({FromJsonStep.DescriptorImpl.class, Jenkins.class})
public class FromJsonStepTest {

    /** Class under test. */
    private FromJsonStep fixture;

    private JSONObject json;
    private Hudson jenkins;


    @Test
    public void doesntCrashWhenInputIsEmpty(){
        fixture = new FromJsonStep(null);
    }

    @Test
    public void returnObjectFromSimpleJson(){
        String jsonString = "{ \"foo\" : \"bar\" }";
        fixture = new FromJsonStep(jsonString);
        Object object = fixture.fromJson();
        assertNotNull(object);
        assertTrue(object instanceof groovy.json.internal.LazyMap);
    }
}
