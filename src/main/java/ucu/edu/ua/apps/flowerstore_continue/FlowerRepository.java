package flowerstore_continue;

import org.springframework.data.jpa.repository.JpaRepository;

import flowers.Flower;
import flowers.FlowerColor;

import java.util.Optional;


public interface FlowerRepository extends JpaRepository<Flower, Long>{
    Optional<Flower> findByColor(FlowerColor color);

}
