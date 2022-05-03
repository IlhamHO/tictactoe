package eu.epfc.j4119.tictactoe.repositories;

import eu.epfc.j4119.tictactoe.entities.Tictactoe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TictactoeRepository extends JpaRepository<Tictactoe,Long> {
}
