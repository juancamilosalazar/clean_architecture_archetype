package co.com.flypass.config;


import co.com.flypass.ports.outbound.ClientPersistencePort;
import co.com.flypass.ports.inbound.ClientUseCasePort;
import co.com.flypass.usecase.ClientUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {
        @Bean
        public ClientUseCasePort clientUseCase(ClientPersistencePort clientPersistence){
                return new ClientUseCase(clientPersistence);
        }
}
