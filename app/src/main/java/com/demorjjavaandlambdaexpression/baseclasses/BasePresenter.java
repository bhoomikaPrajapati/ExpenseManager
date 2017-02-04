package com.demorjjavaandlambdaexpression.baseclasses;


import com.demorjjavaandlambdaexpression.interactor.AppInteractor;

import java.util.HashMap;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created  on 22/8/16.
 */

public abstract class BasePresenter<V extends BaseView> {

  private AppInteractor appInteractor;

  private V view;

  protected CompositeSubscription compositeSubscriptions = new CompositeSubscription();

  protected HashMap<String, Subscription> subscriptionHashMap = new HashMap<>();

  public final void attachView(V view) {
    this.view = view;
  }

  public void detachView() {
    this.view = null;

    compositeSubscriptions.clear();
  }

  public V getView() {
    return view;
  }

  public boolean hasInternet() {
    return view.hasInternet();
  }



  protected void addSubscription(Subscription subscription) {
    compositeSubscriptions.add(subscription);
  }

  //This method is for adding subscription with key
  protected void addSubscription(Subscription subscription, String key, boolean removePrevious) {
    if (removePrevious && subscriptionHashMap.containsKey(key)) {
      Subscription foundSubscription = subscriptionHashMap.get(key);
      if (foundSubscription != null && !foundSubscription.isUnsubscribed()) {
        foundSubscription.unsubscribe();
        subscriptionHashMap.remove(key);
      }
    }
    subscriptionHashMap.put(key, subscription);
  }

  protected final AppInteractor getAppInteractor() {
    if (appInteractor == null) {
      appInteractor = new AppInteractor();
    }
    return appInteractor;
  }
}
