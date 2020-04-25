package Telefon.Files;

import java.util.List;

public interface FileInterface<T>
{
    public T ReadFile();
    public boolean WriteFile(T x, int separator);
}
