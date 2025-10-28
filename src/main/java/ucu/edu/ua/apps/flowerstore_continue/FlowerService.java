package flowerstore_continue;

import java.util.List;

import org.springframework.stereotype.Service;

import flowers.Flower;

@Service
public class FlowerService {
    private FlowerRepository flowerRepository;

    public FlowerService(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    public List<Flower> getFlowers() {
        return flowerRepository.findAll();
    }

    public void addFlower(Flower flower) {
        if (!flowerRepository.findByColor(flower.getFlowerColor()).isPresent()) {
            flowerRepository.save(flower);
        }
    }
}
