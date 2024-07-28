package br.com.felipesoarestech.api.spaceif.api.exceptionhandler;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
public class Problem {
    private OffsetDateTime dataHora;
    private String message;
    private Map<String,String> detalhes;
}
