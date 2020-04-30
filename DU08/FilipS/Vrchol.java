import java.util.Objects;

public class Vrchol {
    public String url;
    public int uroven;

    public Vrchol(String url, int uroven) {
        this.url = url;
        this.uroven = uroven;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUroven() {
        return uroven;
    }

    public void setUroven(int uroven) {
        this.uroven = uroven;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vrchol vrchol = (Vrchol) o;
        return  Objects.equals(url, vrchol.url);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for(char c : url.toCharArray()){
            hash += c;
        }
        return hash;
    }
}
