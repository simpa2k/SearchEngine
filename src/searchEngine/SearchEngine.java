package searchEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import index.Index;
import index.IndexEntry;
import index.Indexer;

public class SearchEngine {


    private HashMap<String, String> documentsById = new HashMap<>();

    private Index index;

    public SearchEngine() {

        String documentA = "This is DSV homepage DSV is a joint department between SU and KTH We have many good students here";
        String documentB = "Hi This is Eriks homepage at DSV a joint department owned by SU and KTH I don't play football but I have a link to Nikos at DSV";
        String documentC = "This is studentexpedition at DSV We have nothing about football sorry But we have a link to Eriks page instead";
        String documentD = "Hi This is Nikos homepage at DSV I like DSV but even more I like football I Nikos am a big fan of football I play football all the time And I also have a Nikos' link to Eriks page";
        String documentE = "This is AIK website We all play football here We think football rocks And we have no links to anybody else we think we are best anyway";

        documentsById.put("A", documentA);
        documentsById.put("B", documentB);
        documentsById.put("C", documentC);
        documentsById.put("D", documentD);
        documentsById.put("E", documentE);

    }

    private void index() {

        index = new Index(documentsById.entrySet());
        index.index();

    }

    private List<Map.Entry<String, IndexEntry<String>>> indexQuery(String query) {

        Indexer indexer = new Indexer();
        Set<Map.Entry<String, IndexEntry<String>>> indexedQuery = indexer.index("query", query);

        return new ArrayList<Map.Entry<String, IndexEntry<String>>>(indexedQuery);
        
    }
    
    private Map<String, Double> normalize(Map<String, Double> relevanceByDocument) {

        Map<String, Double> normalizedRelevanceByDocument = new HashMap<>();

        for(Map.Entry<String, Double> documentRelevanceEntry : relevanceByDocument.entrySet()) {
            
            String documentId = (String) documentRelevanceEntry.getKey();
            Double documentRelevance = (Double) documentRelevanceEntry.getValue();

            String[] words = documentsById.get(documentId).split(" ");
            int wordCount = words.length;

            double normalizedSimilarity = documentRelevance / wordCount;
            normalizedRelevanceByDocument.put(documentId, normalizedSimilarity);

        }

        return normalizedRelevanceByDocument;
        
    }

    private Map<String, Double> calculateScalarProduct(List<Map.Entry<String, IndexEntry<String>>> indexedQuery) {
 
        Map<String, Double> scalarProductByDocument = new HashMap<String, Double>();

        for(Map.Entry<String, IndexEntry<String>> queryTermEntry : indexedQuery) {
            
            String queryTerm = (String) queryTermEntry.getKey();
            IndexEntry<String> indexedQueryTerm = (IndexEntry<String>) queryTermEntry.getValue();

            HashSet<IndexEntry<String>> documentsIncludingQueryTerm = index.get(queryTerm);

            for(IndexEntry<String> document : documentsIncludingQueryTerm) {

                double termWeight = document.getTermWeight() * indexedQueryTerm.getTermWeight();

                String documentId = document.getDocumentId();
                Double documentRelevance = scalarProductByDocument.get(documentId);

                if(documentRelevance == null) {
                    scalarProductByDocument.put(documentId, termWeight);
                } else {
                    scalarProductByDocument.put(documentId, termWeight + documentRelevance);
                }

            }

        }

        return scalarProductByDocument;
       
    }

    private Map<String, Double> calculateNormalizedSimilarity(List<Map.Entry<String, IndexEntry<String>>> indexedQuery) {
     
        return normalize(calculateScalarProduct(indexedQuery));

    }

    private double squareRootOfSumOfSquares(List<Map.Entry<String, IndexEntry<String>>> indexedTerms) {

        double sum = 0;

        for (Map.Entry<String, IndexEntry<String>> indexedTerm : indexedTerms) {
            sum += Math.pow(indexedTerm.getValue().getTermWeight(), 2);
        }

        return Math.sqrt(sum);

    }

    private Map<String, Double> calculateCosineSimilarity(List<Map.Entry<String, IndexEntry<String>>> indexedQuery) {
        
        Map<String, Double> scalarProductByDocument = calculateScalarProduct(indexedQuery);
        Map<String, Double> cosineSimilarityByDocument = new HashMap<>();

        for(Map.Entry<String, Double> document : scalarProductByDocument.entrySet()) {

            String documentId = document.getKey();
            Double scalarProduct = document.getValue();

            cosineSimilarityByDocument.put(documentId, squareRootOfSumOfSquares(indexedQuery) * squareRootOfSumOfSquares());
            
        }
    }

    private Map<String, Double> calculateSimilarity(List<Map.Entry<String, IndexEntry<String>>> indexedQuery) {

         return calculateNormalizedSimilarity(indexedQuery);
        
    }

    public static void main(String[] args) {
        
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.index();

        String query = "Nikos DSV";
        List<Map.Entry<String, IndexEntry<String>>> indexedQuery = searchEngine.indexQuery(query);

        Map<String, Double> relevanceByDocument = searchEngine.calculateSimilarity(indexedQuery);
        System.out.println(relevanceByDocument);

    }
}
