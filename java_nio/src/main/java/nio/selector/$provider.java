package nio.selector;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;

import org.junit.Test;

public class $provider {

    @Test
    public void test() throws IOException {
        SelectorProvider provider1 = SelectorProvider.provider();
        SelectorProvider provider2 = Selector.open().provider();
        System.out.println(provider1);
        System.out.println(provider2);
        System.out.println(provider1==provider2);
    }

}
