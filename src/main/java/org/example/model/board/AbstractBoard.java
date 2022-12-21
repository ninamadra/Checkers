package org.example.model.board;

import org.example.model.Color;
import org.example.model.Field;
import org.example.model.rules.AbstractRules;

import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public abstract class AbstractBoard {

    protected AbstractRules rules;
    protected ArrayList<Field> fields;
    protected Color turn = Color.WHITE;

    public Color getTurn() { return turn; }
    public ArrayList<Field> getFields() {
        return fields;
    }
    public Field getFieldAt(int x, int y) {
        return fields.stream().filter(f -> f.getColumn() == x && f.getRow() == y).findFirst().orElse(null);
    }
    public void move(int oldX, int oldY, int newX, int newY, Color color) throws illegalMoveException {
        Field oldField = fields.stream().filter(f -> f.getColumn() == oldX && f.getRow() == oldY).findFirst().orElse(null);
        Field newField = fields.stream().filter(f -> f.getColumn() == newX && f.getRow() == newY).findFirst().orElse(null);

        ArrayList <Field> capturedFields = new ArrayList<>();
        int i = min(oldX, newX) + 1;
        int j = min(oldY, newY) + 1;
        while ( i < max(oldX, newX)) {
            int finalI1 = i;
            int finalJ1 = j;
            capturedFields.add(fields.stream().filter(f -> f.getColumn() == finalI1 && f.getRow() == finalJ1).findFirst().orElse(null));
            i++;
            j++;
        }

        if(!rules.isMoveValid(oldField, newField, capturedFields, color)) {
            throw new illegalMoveException();
        }


        ArrayList<Field> obligatoryNewFields = findObligatoryMoves(oldField, color);
        if(obligatoryNewFields.size() > 0 && !obligatoryNewFields.contains(newField)) {
            throw new illegalMoveException();
        }

        newField.setColor(oldField.getColor());
        oldField.setColor(Color.NONE);
        newField.setIsKing(oldField.getIsKing());
        oldField.setIsKing(false);

        for ( Field field: capturedFields ) {
             field.setColor(Color.NONE);
             field.setIsKing(false);
        }

        if(isGameOver()) {
            turn = Color.NONE;
        }
        else if( capturedFields.size() == 0 || !isCapturePossible(newField, color)) {
            turn = turn.getOppositeColor();
        }
    }

    protected ArrayList<Field> findObligatoryMoves(Field oldField, Color color) {
        ArrayList<Field> possibleMoves  = new ArrayList<>();
        //TODO wyszukanie obligatoryjnych ruchow
        //wygeneruj possible moves (mozliwe) w zaleznosci od zasad i isKing
        //for each field:possibleMoves
        // if !rules.isMoveValid possibleMoves.remove(field)
                //policz maksymalna liczbe bic i dodaj te ruchy do listy
        ArrayList<Field> obligatoryMoves = new ArrayList<>();
        return obligatoryMoves;
    }
    protected boolean isCapturePossible(Field oldField, Color color) {
        //TODO sprawdzenie czy gracz ma kolejne bicie w swoim ruchu
        //sprawdz bicie na lewej i prawej przekatnej czy ktores jest valid
        //albo wszytskie inne mozliwe dla damki
        return false;
    }
    protected boolean isGameOver() {
        //TODO sprawdzenie czy przeciwnik nie ma juz swojego koloru na planszy albo zadnego ruchu dla kazdego piona
        return false;
    }

    protected boolean isAnotherMovePossible(Field field, Color color) {
        //TODO sprawdzenie isCapturePossible a potem zwycajnych ruchow

        return false;
    }
}

//TODO IMPORTANT!!!!!!!!!!!!!!!
// trzeba sie zastanwoic czy nie wywalic fabryki abstrakcyjnej na rzecz metody wytworczej dla Board,
//         -> wtedy Abstract Rules zamienic na interfejs a obecna implementacje na defaultRules
//         -> przypisanie defaultRules w konstruktorze konkretnej planszy
//         -> spoko wyjscie, bo w tych co wybralismy wariantach te same zasady, a nawet jesli by nie byly mozna nadpisac
//                  i nwm mozna dodac jakies pole static czy bicie obowiazkoawe i wtedy w board.move(..) jesli rules.getIsCapturingObligatory to sprawdzac tylko ale idk
//            QUESTION: czy to ze w intefejsie rules sprawdza sie tylko poprawnosc ruchu i static obowiazek bicia jest ok???
//                  imo chyba tak, bo zasady tylko sprawdzaja czy cos jest poprawne wtedy jak to zasady,
//                  a to board mowi jakie obowiazkowe ruchy sa bo on ma wiedze jak wyglada plansza (nwm imo irl jest tak wlasnie wiec chyba moze byc)