package com.klopov.andrey.rssreader.Model;

import java.util.List;

    public class RSSObject {
    // use rss2json.com and https://www.beautifyconverter.com for http://feeds.feedburner.com/TechCrunch/
    // and https://lifehacker.com/rss

    // correct C# by Java

        // delete { get; set; }
        public String status;
        public Feed feed;
        public List<Item> items;

        public RSSObject(String status, Feed feed, List<Item> items) {
            this.status = status;
            this.feed = feed;
            this.items = items;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Feed getFeed() {
            return feed;
        }

        public void setFeed(Feed feed) {
            this.feed = feed;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }
    }
