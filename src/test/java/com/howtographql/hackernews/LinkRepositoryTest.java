package com.howtographql.hackernews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

public class LinkRepositoryTest {
    private List<Link> initialList;
    private LinkRepository linkRepository;
    
    @Before
    public void beforeEach() {
        initialList = new ArrayList<Link>(
            Arrays.asList(
                new Link("http://howtographql.com", "Foo")
            )
        );
    }

    @Test
    public void getAllLinksWithInitialListReturnsInitialList() {
        linkRepository = new LinkRepository(initialList);
        Assert.assertEquals(initialList, linkRepository.getAllLinks());
    }

    @Test
    public void getAllLinksWithNoListReturnsDefaultList() {
        linkRepository = new LinkRepository();
        List<Link> actual = linkRepository.getAllLinks();
        Assert.assertTrue(actual.isEmpty());
    }

    @Test
    public void saveLinkWithInitialListAugmentsInitialLink() {
        Link addition = new Link("foo.example.comm", "Some other description");

        linkRepository = new LinkRepository(initialList);
        linkRepository.saveLink(addition);

        List<Link> actual = linkRepository.getAllLinks();
        Assert.assertEquals(2, actual.size());
        Assert.assertEquals(initialList.get(0), actual.get(0));
        Assert.assertEquals(addition, actual.get(1));
    }

    @Test
    public void saveLinkWithNoListAguemtnsDefaultList() {
        Link addition = new Link("foo.example.comm", "Some other description");

        linkRepository = new LinkRepository();
        linkRepository.saveLink(addition);

        List<Link> actual = linkRepository.getAllLinks();
        Assert.assertEquals(1, actual.size());
        Assert.assertEquals(addition, actual.get(0));
    }
}