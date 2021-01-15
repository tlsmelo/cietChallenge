package global;

import businessModel.OpenTriviaDBBM;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;
import org.picocontainer.behaviors.Caching;

public class SharedContext {
    private static ThreadLocal<SharedContext> instances = new ThreadLocal<>();
    private static ThreadLocal<OpenTriviaDBBM> openTriviaDBBMInfos = new ThreadLocal<>();
    private final MutablePicoContainer pico;

    private SharedContext() {
        pico = new DefaultPicoContainer(new Caching());
        pico.addComponent(PicoContainer.class, pico);
    }

    public static SharedContext getInstance() {
        if (instances.get() == null) {
            instances.set(new SharedContext());
        }
        return instances.get();
    }

    public static OpenTriviaDBBM getOpenTriviaDBBMInstance() {
        if (openTriviaDBBMInfos.get() == null)
            openTriviaDBBMInfos.set(new OpenTriviaDBBM());
        return openTriviaDBBMInfos.get();
    }

}
