package com.grow.cmputf17team4.grow.Controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.grow.cmputf17team4.grow.Models.App;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.GetImageAble;
import com.grow.cmputf17team4.grow.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class that implements the functionality for image
 * @author Qin Zhang
 * @since 1.0
 */


public class ImageManager {
    private String mCurrentPhotoPath;
    private GetImageAble getImageAble;
    /**
     * Inner class to encode image
     */
    private class EncodeImageTask extends AsyncTask<Void,Void,Void>{
        private int height;
        private int width;
        private ImageView imageView;
        private Uri uri;


        EncodeImageTask(ImageView imageView,Uri uri) {
            this.imageView = imageView;
            this.height = imageView.getHeight();
            this.width = imageView.getWidth();
            this.uri = uri;
        }

        private Bitmap loadImage() throws IOException{
            Bitmap image;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            ParcelFileDescriptor parcelFileDescriptor =
                    App.getContext().getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();

            // get size
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(fileDescriptor,null,bmOptions);
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;
            int scaleFactor = Math.min(photoW/width, photoH/height);

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;

            image = BitmapFactory.decodeFileDescriptor(fileDescriptor,null,bmOptions);
            parcelFileDescriptor.close();
            return image;

        }


        /**
         * Encode the image to byte array
         * @param image
         */
        private void encodeImage(Bitmap image){
            ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100,
                    byteArrayBitmapStream);
            byte[] b = byteArrayBitmapStream.toByteArray();
            getImageAble.setEncodedImage(Base64.encodeToString(b, Base64.DEFAULT));
        }

        /**
         *
         * @param params
         * @return
         */
        @Override
        protected Void doInBackground(Void... params) {
            try{
                encodeImage(loadImage());
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new LoadBytesImageTask(imageView).execute();
        }
    }


    private class LoadBytesImageTask extends AsyncTask<Void,Void,Void>{
        private ImageView imageView;
        private int height;
        private int width;
        private Bitmap image;

        LoadBytesImageTask(ImageView imageView){
            this.imageView = imageView;
            this.height = imageView.getHeight();
            this.width = imageView.getWidth();
        }

        @Override
        protected Void doInBackground(Void... params) {
            byte[] decodedString = Base64.decode(getImageAble.getEncodedImage(), Base64.DEFAULT);
            image = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            imageView.setImageBitmap(image);
        }
    }

    public ImageManager(GetImageAble getImageAble) {
        this.getImageAble = getImageAble;
    }

    public void dispatchTakePictureIntent(Context context) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(context);
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(context,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                ((AppCompatActivity)context).startActivityForResult(takePictureIntent, Constant.REQUEST_TAKE_PHOTO);
            }
        }
    }


    private File createImageFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void setPic(final ImageView imageView){

        if (getImageAble.getEncodedImage()==null){
            return;
        }

        imageView.post(new Runnable() {
            @Override
            public void run() {
                new LoadBytesImageTask(imageView).execute();
            }
        });

    }

    public void encode(final ImageView imageView, final Uri uri){
        imageView.post(new Runnable() {
            @Override
            public void run() {
                new EncodeImageTask(imageView,uri).execute();
            }
        });
    }


    public void encode(final ImageView mImageView){
        final File file = new File(mCurrentPhotoPath);

        mImageView.post(new Runnable() {
            @Override
            public void run() {
                new EncodeImageTask(mImageView,Uri.fromFile(file)).execute();
            }
        });

    }

    public void getPictureFromGalleryIntent(Context context){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)

        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("image/*");

        ((AppCompatActivity)context).startActivityForResult(intent, Constant.REQUEST_PICK_IMAGE);
    }



}
