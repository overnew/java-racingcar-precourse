package racingcar.utils;

import java.util.ArrayList;
import java.util.List;

import racingcar.constants.ConstMessage;
import racingcar.constants.ConstValue;

import camp.nextstep.edu.missionutils.Console;

public class NamesInputCheck {

    public static List<String> getRightNamesInput(){
        List<String> names;

        do{
            System.out.println(ConstMessage.NAMES_INPUT_REQUEST);

            names = parseNames(Console.readLine());
        }while(!checkIsPossibleNames(names));

        return names;
    }

    private static List<String> parseNames(String input){
        int parsingBeginIndex=0, parsingEndIndex;
        List<String> names = new ArrayList<>();

        while((parsingEndIndex = input.indexOf(ConstMessage.NAME_PARSING_DELIMITER, parsingBeginIndex)) != -1){
            names.add(input.substring(parsingBeginIndex, parsingEndIndex));
            parsingBeginIndex = parsingEndIndex+1;
        }

        //마지막 이름 넣어주기
        names.add(input.substring(parsingBeginIndex));
        return names;
    }

    private static boolean checkIsPossibleNames(List<String> names){
        boolean isPossibleName = true;

        try{
            checkNamesLength(names);
            checkHaveSameNames(names);
            checkHaveEmptyNames(names);
        }catch(IllegalArgumentException exception){
            System.out.println(exception.getMessage());
            isPossibleName = false;
        }

        return isPossibleName;
    }

    private static void checkNamesLength(List<String> names) throws IllegalArgumentException{

        for(String name : names){

            if(name.length() > ConstValue.NAME_LENGTH_LIMIT){
                throw new IllegalArgumentException(ConstMessage.NAME_INPUT_ERROR);
            }

        }

    }

    private static void checkHaveSameNames(List<String> names) throws IllegalArgumentException{

        for(String name: names){
            int fromFront = names.indexOf(name);
            int fromLast = names.lastIndexOf(name);

            if(fromFront != fromLast){
                throw new IllegalArgumentException(ConstMessage.SAME_NAME_ERROR + name);
            }

        }

    }

    public static void checkHaveEmptyNames(List<String> names) throws IllegalArgumentException{

        for(String name: names){

            if(name.length() == 0){
                throw new IllegalArgumentException(ConstMessage.EMPTY_NAME_ERROR);
            }

        }

    }

}
