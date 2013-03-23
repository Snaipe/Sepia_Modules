import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.spout.api.util.config.ConfigurationHolder;
import org.spout.vanilla.data.configuration.VanillaConfiguration;

import net.minekingdom.Sepia.environment.heap.Bucket;
import net.minekingdom.Sepia.environment.heap.ConstantBucket;
import net.minekingdom.Sepia.environment.heap.DynamicMethodBucket;
import net.minekingdom.Sepia.script.value.structure.Module;

public class Vanilla extends Module {

    public Vanilla() {
        super("Vanilla", getVanillaMapping());
    }

    private static Map<String, Bucket> getVanillaMapping() {
        Map<String, Bucket> map = new HashMap<String, Bucket>();
        
        map.put("name", new ConstantBucket("Vanilla"));
        try {
            Method motd   = ConfigurationHolder.class.getMethod("getString");
            Method online = ConfigurationHolder.class.getMethod("getBoolean");
            
            map.put("motd",   new DynamicMethodBucket(motd, VanillaConfiguration.MOTD));
            map.put("online", new DynamicMethodBucket(online, VanillaConfiguration.ONLINE_MODE));
        } catch (Exception ex) {}
        
        return map;
    }

}
