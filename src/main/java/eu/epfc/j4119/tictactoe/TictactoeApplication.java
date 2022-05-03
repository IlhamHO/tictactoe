package eu.epfc.j4119.tictactoe;

import eu.epfc.j4119.tictactoe.entities.Tictactoe;
import eu.epfc.j4119.tictactoe.repositories.TictactoeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Scanner;

@Transactional
@AllArgsConstructor
@SpringBootApplication
public class TictactoeApplication implements CommandLineRunner {
	private TictactoeRepository tictactoeRepository;

	public static void main(String[] args) {
		SpringApplication.run(TictactoeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Choose one of the options here below:");
			System.out.println("1. create a game");
			System.out.println("2. show games list");
			System.out.println("3. modify the game");
			System.out.println("4. delete the game");
			System.out.println("Q. leave the game");
			String choice = scanner.nextLine();
			if(choice.equals("Q")) break;
			switch (choice){
				case "1":
					Tictactoe tictactoe = new Tictactoe();
					System.out.print("PlayerX, enter your name: ");
					String playerX = scanner.nextLine();
					System.out.print("PlayerO, enter your name: ");
					String playerO = scanner.nextLine();
					System.out.print("enter the cases: 1");
					String cases = scanner.nextLine();
					tictactoe.setPlayerX(playerX);
					tictactoe.setPlayerO(playerO);
					tictactoe.setCases(cases);
					tictactoeRepository.save(tictactoe);
					break;
				case "2":
					List<Tictactoe> tictactoes = tictactoeRepository.findAll();
					for(Tictactoe t : tictactoes) System.out.println(t);
					break;
				case "3":
					System.out.println("enter the id of the game to modify: ");
					long id = Long.parseLong(scanner.nextLine());
					tictactoeRepository.findById(id);
					System.out.print("PlayerX, enter your name: ");
					String playerx = scanner.nextLine();
					System.out.print("PlayerO, enter your name: ");
					String playero = scanner.nextLine();
					System.out.print("enter the cases: ");
					String newCases = scanner.nextLine();
					tictactoeRepository.save(new Tictactoe(id,playerx,playero,newCases));
					break;
				case"4":
					System.out.println("enter the id of the game to delete: ");
					id = Long.parseLong(scanner.nextLine());
					tictactoeRepository.deleteById(id);
					break;
				default:
					System.out.println("the choice"+choice+" is not known");
			}
		}while (true);
	}
}
