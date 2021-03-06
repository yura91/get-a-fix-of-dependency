package com.wordpress.laaptu.dependencyinjection;

import android.app.Application;
import com.wordpress.laaptu.dependencyinjection.dagger.AndroidModule;
import com.wordpress.laaptu.dependencyinjection.dagger.DaggerDataComponent;
import com.wordpress.laaptu.dependencyinjection.dagger.DataComponent;
import com.wordpress.laaptu.dependencyinjection.dagger.DataModule;

/**
 */

public class MainApplication extends Application {

  @Override public void onCreate() {
    super.onCreate();
  }

  private final DataComponent dataComponent = createDataComponent();

  protected DataComponent createDataComponent() {
    return DaggerDataComponent.builder()
        .dataModule(new DataModule())
        .androidModule(new AndroidModule(this))
        .build();
  }

  public DataComponent getDataComponent() {
    return dataComponent;
  }
}
