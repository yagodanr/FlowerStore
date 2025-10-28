package flowerstore_continue;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import flowers.Flower;
import flowers.FlowerColor;
import flowers.FlowerType;


@RestController
@RequestMapping("/flowers")
public class FlowerController {
    //CHECKSTYLE:OFF
    @GetMapping("/")
    public List<Flower> getFlowers() {
        return List.of(
            new Flower(),
            new Flower(15.0, FlowerColor.GREEN, 475.0, FlowerType.ROSE),
            new Flower(20.0, FlowerColor.WHITE, 75.0, FlowerType.CHAMOMILE)
        );
    }
    //CHECKSTYLE:ON

}
