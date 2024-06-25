package pds.comasy.patterns.votingsStrategy;

import pds.comasy.entity.Suggestion;

import java.util.List;

public interface SuggestionStrategy {
    List<Suggestion> getSuggestions(List<Suggestion> suggestions);
}

