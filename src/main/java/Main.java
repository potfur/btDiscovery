import application.Controller;
import org.codejargon.feather.Feather;
import org.codejargon.feather.Key;

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        String action = getAction(args, "list");
        Map<String, String> arguments = getArguments(args);

        Feather services = Feather.with(new Services());
        Map<String, String> mapping = new HashMap<>();

        mapping.put("list", "device-list");
        mapping.put("discover", "device-discover");
        mapping.put("details", "device-details");

        try {
            Controller controller = services.instance(Key.of(Controller.class, mapping.get(action)));
            System.out.println(controller.handle(arguments));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getAction(String[] args, String defaultAction)
    {
        return args.length == 0 ? defaultAction : args[0];
    }

    private static Map<String, String> getArguments(String[] args)
    {
        Map<String, String> arguments = new HashMap<>();

        for (int i = 0; i < args.length; i += 2) {
            arguments.put(
                args[i],
                args.length > i + 1 ? args[i + 1] : null
            );
        }

        return arguments;
    }
}
