package pds.comasy.patterns.votingsStrategy;

import pds.comasy.entity.Suggestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategorizedSuggestionStrategy implements SuggestionStrategy {
    @Override
    public List<Suggestion> getSuggestions(List<Suggestion> suggestions) {
        Map<String, List<Suggestion>> categorizedSuggestions = suggestions.stream()
                .collect(Collectors.groupingBy(Suggestion::getType));
        List<Suggestion> result = new ArrayList<>();
        categorizedSuggestions.forEach((category, suggestionList) -> result.addAll(suggestionList));
        return result;
    }
}
