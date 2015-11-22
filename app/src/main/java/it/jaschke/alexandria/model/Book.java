package it.jaschke.alexandria.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Book implements Parcelable {

  private final String title;
  private final String imageUrl;
  private final List<String> authors;

  public Book(String title,String imageUrl, List<String> authors){
    this.title = title;
    this.imageUrl = imageUrl;
    this.authors = authors;
  }

  public String getTitle() {
    return title;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public List<String> getAuthors() {
    return authors;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.title);
    dest.writeString(this.imageUrl);
    dest.writeStringList(this.authors);
  }

  protected Book(Parcel in) {
    this.title = in.readString();
    this.imageUrl = in.readString();
    this.authors = in.createStringArrayList();
  }

  public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
    public Book createFromParcel(Parcel source) {
      return new Book(source);
    }

    public Book[] newArray(int size) {
      return new Book[size];
    }
  };
}
