package index;

public class IndexEntry<T> {

    private T documentId;
    private int documentWordCount;
    private double termWeight;
    private int termPosition;

    public IndexEntry(T documentId, int documentWordCount, double termWeight, int termPosition) {

        this.documentId = documentId;
        this.documentWordCount = documentWordCount;
        this.termWeight = termWeight;
        this.termPosition = termPosition;
        
    }

    public T getDocumentId() {
        return documentId;
    }

    public int getDocumentWordCount() {
        return documentWordCount;
    }

    public double getTermWeight() {
        return termWeight;
    }

    public int getTermPosition() {
        return termPosition;
    }

    public void setTermWeight(double termWeight) {
       this.termWeight = termWeight; 
    }

    @Override
    public String toString() {
        return String.format("Document ID: %s, Term weight: %f, Term position: %d", documentId, termWeight, termPosition);
    }
}
