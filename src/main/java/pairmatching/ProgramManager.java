package pairmatching;

import java.util.List;
import camp.nextstep.edu.missionutils.Console;

public class ProgramManager {

	public static boolean selectFunction(){
		//ProgramPrinter.selectFunction();
		String selection = Console.readLine();
		ProgramPrinter.printSelectInfo();

		if(selection == "1"){
			String selectLine = Console.readLine();
			String[] selectedComponent = parseLine(selectLine);
			PairMatcher.pairMatch(Course.valueOf(selectedComponent[0]), Level.valueOf(selectedComponent[1]), selectedComponent[2]);
			return false;
		} else if(selection == "2"){
			String selectLine = Console.readLine();
			String[] selectedComponent = parseLine(selectLine);
			ProgramManager.lookUpPair(Course.valueOf(selectedComponent[0]), Level.valueOf(selectedComponent[1]), selectedComponent[2]);
			return false;
		} else if(selection == "3"){
			ProgramManager.initializePair();
			return false;
		} else if(selection == "Q"){
			return true;
		}

		throw new IllegalArgumentException("[ERROR] 유효하지 않은 명령입니다.");

	}

	public static String[] parseLine(String selectLine){
		String[] selectedComponent = selectLine.split(", ");
		return selectedComponent;
	}

	public static void lookUpPair(Course course, Level level, String mission){
		List<PairMatchRecorder> checkList = ProgramData.getMatchingHistory(course, level);

		for(PairMatchRecorder history : checkList){
			if(!history.isMatchedMission(mission)){
				ProgramPrinter.printPairInfo(history.getPairMatchHistory());
				return;
			}
		}

		System.out.println("[ERROR] 매칭 이력이 없습니다.");
		return;
	}

	public static void initializePair(){
		ProgramData.deleteMatchingHistory();
		ProgramPrinter.printInitialize();
	}
}
