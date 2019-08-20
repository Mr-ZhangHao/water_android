package com.exchange.water.application.utils;


import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by lion on 2017/3/14.
 */

public class ExEventBus {


    private static ExEventBus instance;

    public static ExEventBus getDefault() {
        if (instance == null) {
            instance = new ExEventBus();
        }
        return instance;
    }


    public EventBus getDefaultEventBus() {
        return EventBus.getDefault();
    }

    public void sendEvent(int eventType) {
        MessageEvent event = new MessageEvent(eventType);
        getDefaultEventBus().post(event);
    }

    public void sendEvent(MessageEvent event) {
        getDefaultEventBus().post(event);
    }

    public void startFragment(SupportFragment fragment) {


        getDefaultEventBus().post(new MessageFragment(fragment));
    }

    public void startFragmentForResult(SupportFragment fragment, int requestCode) {
        getDefaultEventBus().post(new MessageFragment(fragment, requestCode));
    }

    /**
     * event bus event message
     */
    public static class MessageEvent {

        public static final int EVENT_TYPE_JUMP_TRADE = 1;



        /**
         * the event type,
         * separate the event with the {this#type}
         */
        private int type;

        /**
         * the event data,
         * the event with the {this#data}
         */
        private Object data;


        public MessageEvent() {
        }

        public MessageEvent(int type) {
            this(type, null);
        }

        public MessageEvent(int type, Object data) {
            this.type = type;
            this.data = data;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

    public static class MessageFragment {

        public static final int REQUEST_CODE_NORMAL                 = -1;
        public static final int REQUEST_PERMISSION_EXTERNAL_STORAGE = 1;

        private SupportFragment fragment;

        private int requestCode;

        public MessageFragment(SupportFragment fragment) {
            this(fragment, REQUEST_CODE_NORMAL);
        }

        public MessageFragment(SupportFragment fragment, int requestCode) {
            this.fragment = fragment;
            this.requestCode = requestCode;
        }

        public SupportFragment getFragment() {
            return fragment;
        }

        public void setFragment(SupportFragment fragment) {
            this.fragment = fragment;
        }

        public int getRequestCode() {
            return requestCode;
        }

        public void setRequestCode(int requestCode) {
            this.requestCode = requestCode;
        }
    }
}
