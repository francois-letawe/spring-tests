package be.rnb.test.proxy;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;

public class PreFilter extends AbstractGatewayFilterFactory<PreFilter.Config> {
    Logger logger = LoggerFactory.getLogger(PreFilter.class);

    public PreFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(PreFilter.Config config) {
        return (exchange, chain) -> {
            logger.debug("pre prefilter called -> " + config.getName());

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        logger.debug("post prefilter called -> " + config.getName());
                    }));
        };
    }

    @Data
    public static class Config {
        private String name;
    }
}
