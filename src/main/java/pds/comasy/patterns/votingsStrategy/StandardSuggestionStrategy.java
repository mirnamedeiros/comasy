package pds.comasy.patterns.votingsStrategy;

import pds.comasy.entity.Suggestion;

import java.util.List;

public class StandardSuggestionStrategy implements SuggestionStrategy {
    @Override
    public List<Suggestion> getSuggestions(List<Suggestion> suggestions) {
        return suggestions;
    }
}
