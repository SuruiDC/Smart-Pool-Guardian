package pe.upc.smartpoolguardian.servicesimplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.upc.smartpoolguardian.entities.DetalleMedicion;
import pe.upc.smartpoolguardian.repositories.IMedicionDetalleRepository;
import pe.upc.smartpoolguardian.servicesinterfaces.IGeminiService;
import java.util.List;
import java.util.Map;

@Service
public class GeminiServiceImplement implements IGeminiService {
    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    @Autowired
    private IMedicionDetalleRepository detalleMedicionRepository;

    private final WebClient webClient = WebClient.create();

    @Override
    public String obtenerRecomendacion(Integer piscinaId) {
        DetalleMedicion detalle = detalleMedicionRepository
                .findUltimaByPiscinaId(piscinaId)
                .orElseThrow(() -> new RuntimeException("No hay mediciones para esta piscina"));

        String prompt = String.format(
                "Eres un experto en mantenimiento de piscinas. " +
                        "Analiza estos valores de la última medición: " +
                        "pH=%.1f, Cloro=%.1f ppm, Temperatura=%.1f°C, " +
                        "Turbidez=%.1f, Alcalinidad=%.1f, Dureza de calcio=%.1f, " +
                        "Tiene algas=%s, Color del agua=%s, Olor=%s. " +
                        "Da una recomendación práctica en español, máximo 3 oraciones.",
                detalle.getNivelPh(),
                detalle.getNivelCloro(),
                detalle.getTemperatura(),
                detalle.getNivelTurbidez(),
                detalle.getAlcalinidad(),
                detalle.getDurezaCalcio(),
                detalle.isTieneAlgas() ? "Sí" : "No",
                detalle.getColorPiscina(),
                detalle.getOlor()
        );

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        Map response = webClient.post()
                .uri(apiUrl + "?key=" + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<Map> candidates = (List<Map>) response.get("candidates");
        Map content = (Map) candidates.get(0).get("content");
        List<Map> parts = (List<Map>) content.get("parts");
        return (String) parts.get(0).get("text");
    }
}
