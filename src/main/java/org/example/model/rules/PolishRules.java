package org.example.model.rules;

public class PolishRules extends AbstractRules {
    @Override
    public boolean isMoveValid() {
        return false;
    }

    @Override
    public boolean isWin() {
        return false;
    }
}
