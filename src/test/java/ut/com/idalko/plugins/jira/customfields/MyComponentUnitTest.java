package ut.com.idalko.plugins.jira.customfields;

import org.junit.Test;
import com.idalko.plugins.jira.customfields.api.MyPluginComponent;
import com.idalko.plugins.jira.customfields.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}