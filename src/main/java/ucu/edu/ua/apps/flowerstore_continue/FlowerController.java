package flowerstore_continue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import flowers.Flower;
import flowers.FlowerColor;
import flowers.FlowerType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/flowers")
public class FlowerController {
    private final FlowerService flowerService;

    @Autowired
    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping("/")
    public List<Flower> getFlowers() {
        return List.of(
            new Flower(),
            new Flower(1L, 15.0, FlowerColor.GREEN, 475.0, FlowerType.ROSE),
            new Flower(2L, 20.0, FlowerColor.WHITE, 75.0, FlowerType.CHAMOMILE)
        );
    }


    @PostMapping("/")
    public void addFlower(@RequestBody Flower flower) {
        flowerService.addFlower(flower);
    }


}
