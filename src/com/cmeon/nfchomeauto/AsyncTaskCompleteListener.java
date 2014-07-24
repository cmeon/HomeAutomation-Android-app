package com.cmeon.nfchomeauto;

// this is an interface that handles the asynctask callbacks
interface AsyncTaskCompleteListener<T>
{
    public void onTaskComplete(T result);
}
