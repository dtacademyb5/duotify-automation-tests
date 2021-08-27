package dbtests;

import com.mysql.cj.log.Log;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PlaylistsPage;
import uitests.TestBase;
import utilities.DBUtility;
import utilities.SeleniumUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BusinessRulesTest extends TestBase {


    @Test
    public void verifyAlbumsTableColumnNames(){

        List<String> expectedColumnNames = Arrays.asList("id", "title", "artist" , "genre", "artworkPath");

        List<String> actualColumnNames = DBUtility.getColumnNames("select * from albums limit 1");

        Assert.assertEquals(actualColumnNames, expectedColumnNames);

    }

    @Test
    public void verifyUSersTableColumnNames(){

        List<String> expectedColumnNames = Arrays.asList("id", "username", "firstName" , "lastName", "email", "password", "signUpDate", "profilePic");

        List<String> actualColumnNames = DBUtility.getColumnNames("select * from users limit 1");

        Assert.assertEquals(actualColumnNames, expectedColumnNames);

    }

    @Test
    public void verifyTheExpectedGenresList(){

        List<String> expectedGenres = Arrays.asList("rap",
                "pop",
                "techno" ,
                "rnb" ,
                "house",
                "classical" ,
                "jazz" ,
                "electronic" ,
                "dance" ,
                "reggae" ,
                "reggaeton");


        List<List<Object>> list = DBUtility.getQueryResultAsListOfLists("select name from genres");

        System.out.println(list);

        List<String> actualGenres =  new ArrayList<>();
        for (List<Object> row : list) {
           actualGenres.add((String) (row.get(0)));
        }

        Assert.assertEquals(actualGenres, expectedGenres);


    }


    @Test
    public void verifyPlaylistNameSupportsUnicodeCharsAndVerifyUpdateOnUI(){


        String expectedName = "あおい";
        String quesry = "UPDATE playlists SET name='"+expectedName+"' where id='3'";
        // Send update query to update the playlist name to non-english char
        DBUtility.updateQuery(quesry);

        // Send retrieve query to verify that the playlistName has been updated correctly

       Map<String, Object> map = DBUtility.getQueryResultListOfMaps("select * from playlists where id='3'").get(0);

        String actualNamefromDb = (String)(map.get("name"));

        Assert.assertEquals(actualNamefromDb, expectedName);

        // Verify the playlist creation on the UI


        new LoginPage().login("duotech", "duotech");
        new LoginPage().yourMusicLink.click();

        List<String> allPlayLists = SeleniumUtils.getElementsText(new PlaylistsPage().allPlaylistsList);

        Assert.assertTrue(allPlayLists.contains(expectedName));


    }




}
