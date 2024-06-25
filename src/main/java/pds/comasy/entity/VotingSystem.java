package pds.comasy.entity;

import pds.comasy.patterns.votingsStrategy.SuggestionStrategy;

import java.util.List;

public class VotingSystem {
    private SuggestionStrategy suggestionStrategy;

    public VotingSystem(SuggestionStrategy suggestionStrategy) {
        this.suggestionStrategy = suggestionStrategy;
    }

    public List<Suggestion> getSuggestions(List<Suggestion> suggestions) {
        return suggestionStrategy.getSuggestions(suggestions);
    }
}
